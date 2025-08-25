import json
sample = {"celestial_blessing":{"tier":"S","icon_url":"https://raw.githubusercontent.com/placeholder/meta-repo/main/icons/celestial_blessing.png"},"second_wind":{"tier":"A","icon_url":"https://raw.githubusercontent.com/placeholder/meta-repo/main/icons/second_wind.png"}}
with open('backend/meta.json','w') as f: json.dump(sample,f,indent=2)
