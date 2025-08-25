from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from backend.meta import get_meta, recommend

app = FastAPI()
app.add_middleware(CORSMiddleware, allow_origins=["*"], allow_methods=["*"], allow_headers=["*"])

@app.get("/meta")
def meta():
    return get_meta()

@app.post("/recommend")
def rec(augments: dict):
    return recommend(augments)