from fastapi import FastAPI
import json, os
app = FastAPI()
@app.get('/meta')
def meta():
    p = os.path.join(os.path.dirname(__file__), 'meta.json')
    with open(p) as f: return json.load(f)
@app.post('/recommend')
def recommend(payload: dict):
    offered = payload.get('augments', [])
    return {'best': offered[0] if offered else None}
