def recommend_comp(hp, gold, scout, meta):
    if gold >= 60:
        return "Hyper-roll Comp"
    if hp < 30:
        return "Defensive Comp"
    return "Balanced Meta Comp"
