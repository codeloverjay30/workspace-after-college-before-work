import pprint
import pandas as pd
pp = pprint.PrettyPrinter(indent=0)
data = pd.read_csv("nutrition.csv")
pp.pprint(data)