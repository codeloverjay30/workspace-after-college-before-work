import 'package:flame/flame.dart';
import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:obs/obs.dart';


void main() {
  WidgetsFlutterBinding.ensureInitialized();
  Flame.device.fullScreen();
  runApp(const GameApp());
}

class GameApp extends StatelessWidget {
  const GameApp({super.key});

  @override
  Widget build(BuildContext context) {
    final getIt = GetIt.instance;
    var game = BreakerGame();
    final gameStore = getIt.get<GameStore>();
    return Observer(
      builder: (context) {
        switch (gameStore.gameState) {
          case GameState.launch:
            return const LaunchScreen();
          case GameState.play:
            if (gameStore.doGameRestart) {
              game = BreakerGame();
              gameStore
                ..doGameRestart = false
                ..resetScore();
            }
            return GameBoard(game: game);
          case GameState.pause:
            return const PauseScreen();
          case GameState.gameOver:
            return const GameOverScreen();
        }
      },
    );
  }
}

class GameBoard extends StatelessWidget {
  const GameBoard({super.key, required this.game});

  final BreakerGame game;

  @override
  Widget build(BuildContext context) {
    final gameStore = getIt.get<GameStore>();
    return Column(
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        const SizedBox(height: 20),
        SvgPicture.asset(
          'assets/logo.svg',
          width: 200,
        ),
        const SizedBox(height: 20),
        Container(
          padding:
          const EdgeInsets.symmetric(horizontal: 14, vertical: 8),
          color: GameConstants.backGroundColor,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Observer(
                builder: (context) {
                  return GestureDetector(
                    onTap: () => gameStore.gameState = GameState.pause,
                    child: Icon(
                      gameStore.paused ? Icons.play_arrow : Icons.pause,
                      color: GameConstants.whiteColor,
                    ),
                  );
                },
              ),
              Observer(
                builder: (context) {
                  return Text(
                    gameStore.score.toString(),
                    style: const TextStyle(
                      color: GameConstants.whiteColor,
                      fontSize: 26,
                    ),
                  );
                },
              ),
              Column(
                children: [
                  const Text(
                    'Best',
                    style: TextStyle(
                      color: GameConstants.whiteColor,
                      fontSize: 18,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Observer(
                    builder: (context) {
                      return Text(
                        gameStore.highScore.toString(),
                        style: const TextStyle(
                          color: GameConstants.whiteColor,
                          fontSize: 22,
                        ),
                      );
                    },
                  ),
                ],
              )
            ],
          ),
        ),
        const SizedBox(height: 10),
        Expanded(
          child: GameWidget(game: game),
        ),
        const SizedBox(height: 100)
      ],
    );
  }
}

class BreakerGame extends FlameGame with HasCollisionDetection, HasDraggables {

  GetIt get getIt => GetIt.instance;

  @override
  Color backgroundColor() => GameConstants.panelColor;
  GameStore get gameStore => getIt.get<GameStore>();
  final ball = DraggableBall();
  final board = Board();

  @override
  Future<void> onLoad() async {
    ball.priority = 1;
    gameStore
      ..score = noBricksLayer
      ..ballState = BallState.ideal;
    await loadInitialBrickLayer();
    await add(board);
    await add(ball);
  }

  double get brickSize {
    const totalPadding = (GameConstants.noBricksInRow + 1) * GameConstants.brickPadding;
    final screenMinSize = size.x < size.y ? size.x : size.y;
    return (screenMinSize - totalPadding) / GameConstants.noBricksInRow;
  }
  List<BrickComponent> getBrickLayer() {
    return List<BrickComponent>.generate(
      GameConstants.noBricksInRow,
          (index) => BrickComponent(
        counter: random.fromRange(-3, GameConstants.maxValueOfBrick),
        size: brickSize,
      ),
    );
  }

  Future<void> loadInitialBrickLayer() async {
    for (var row = 0; row < noBricksLayer; row++) {
      final bricksLayer = getBrickLayer();
      for (var i = 0; i < GameConstants.noBricksInRow; i++) {
        final xPosition = i == 0
            ? padding
            : bricksLayer[i - 1].position.x +
            bricksLayer[i - 1].size.x +
            padding;
        final yPosition = (row + 1) * (brickSize + padding);
        await add(bricksLayer[i]..position = Vector2(xPosition, yPosition));
      }
    }
  }
}

class BrickComponent extends RectangleComponent
    with CollisionCallbacks, HasGameRef<BreakerGame> {
  BrickComponent({
    required this.counter,
    required double size,
  }) : super(
    size: Vector2.all(size),
    paint: Paint()
      ..style = PaintingStyle.fill
      ..color = GameConstants.brickColor,
  );

  int counter;
  bool hasCollided = false;
  late final TextComponent textComponent;

  @override
  Future<void> onLoad() async {

    //1
    if (counter <= 0) {
      removeFromParent();
      return;
    }

    //2
    textComponent = TextComponent(
      text: '$counter',
      textRenderer: TextPaint(
        style: const TextStyle(
          color: GameConstants.whiteColor,
          fontSize: GameConstants.brickFontSize,
        ),
      ),
    )..center = size / 2;

    await add(textComponent);

    //3
    await add(RectangleHitbox());
  }

  //4
  @override
  void update(double dt) {
    if (hasCollided) {
      textComponent.text = '$counter';
    }
  }

  //5
  @override
  void onCollision(Set<Vector2> intersectionPoints, PositionComponent other) {
    if (other is Ball && !hasCollided) {
      hasCollided = true;
      if (--counter == 0) {
        removeFromParent();
        return;
      }
    }
    super.onCollision(intersectionPoints, other);
  }

  @override
  void onCollisionEnd(PositionComponent other) {
    if (hasCollided) {
      hasCollided = false;
    }
    super.onCollisionEnd(other);
  }
}