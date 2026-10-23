import pprint
import pandas as pd
pp = pprint.PrettyPrinter(indent=4)
data = pd.read_csv("nutrition.csv")
pp.pprint(data.columns)
columnToFilter = [
    "整合編號",
    "樣品名稱",
    "熱量(kcal)",
    "粗蛋白(g)",
    "膽固醇(mg)",
    "粗脂肪(g)",
    "總碳水化合物(g)",
]
newData = data.filter()