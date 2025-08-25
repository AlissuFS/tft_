def get_meta():
    # Retorna exemplo de meta
    return {
        "celestial_blessing": "http://example.com/icons/celestial_blessing.png",
        "second_wind": "http://example.com/icons/second_wind.png",
        "thrill_of_the_hunt": "http://example.com/icons/thrill_of_the_hunt.png"
    }

def recommend(augments: dict):
    offered = augments.get("augments", [])
    best = offered[0] if offered else "N/A"
    return {"best": best}