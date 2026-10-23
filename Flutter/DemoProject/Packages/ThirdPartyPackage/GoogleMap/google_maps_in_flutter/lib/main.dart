import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';

import 'firebase_options.dart';

var logger = Logger(
  printer: PrettyPrinter(),
);

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Ice Cream FTW',
      theme: ThemeData(
        primarySwatch: Colors.pink,
        scaffoldBackgroundColor: Colors.pink[50],
      ),
      home: const MyHomePage(title: 'Ice Cream Stores in SF'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Stream<QuerySnapshot<Map<String, dynamic>>>? _userInfoStores;
  CollectionReference<Map<String, dynamic>>? _collectionReference;


  @override
  void initState() {
    super.initState();
    checkCurrentAuthState();
    initData();
    //addData();
    collectData();
  }

  void checkCurrentAuthState(){
    FirebaseAuth.instance
        .authStateChanges()
        .listen((User? user) {
      if (user == null) {
        if (kDebugMode) {
          print('User is currently signed out!');
        }
      } else {
        if (kDebugMode) {
          print('User is signed in!');
        }
      }
    });
  }
  void initData(){
    _collectionReference = FirebaseFirestore.instance.collection("users");
  }

  void addData(){
    // Create a new user with a first and last name
    final user = <String, dynamic>{
      "first": "Ada",
      "last": "Lovelace",
      "born": 1815
    };

    // Add a new document with a generated ID
    _collectionReference?.add(user).then((DocumentReference doc) =>
        print('DocumentSnapshot added with ID: ${doc.id}'));
  }

  void collectData(){
    _userInfoStores = _collectionReference
        ?.orderBy('first')
        .snapshots();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Center(child: Text(widget.title))),
        body: StreamBuilder<QuerySnapshot>(
          stream: _userInfoStores,
          builder: (context, snapshot) {
            if(_userInfoStores == null){
              logger.e('Couldn\'t find the database from firebase successfully.');
              return Center(child: Text('Couldn\'t find the database from firebase successfully.'));
            }
            if (snapshot.hasError) {
              logger.e('Error:${snapshot.error}');
              return Center(child: Text('Error:${snapshot.error}'));
            }
            if (!snapshot.hasData) {
              logger.e('Loading:...');
              return const Center(child: Text('Loading:...'));
            }

            final docs = snapshot.data?.docs;
            final storesCount = docs?.length ?? 0;
            if (storesCount == 0) {
              logger.e('no results found');
              return Center(child: Text('no results found'));
            }

            logger.e('There are $storesCount results');
            return ListView.builder(
              itemCount: storesCount,
              itemBuilder: (builder, index) {
                final doc = docs?[index];
                return ListTile(
                  title: Text(doc?['name']),
                  subtitle: Text(doc?['address']),
                );
              },
            );
          },
        )
    );
  }
}
