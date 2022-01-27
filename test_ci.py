import requests
import pytest
import json


def test_health_check():
    r = requests.get("http://localhost:8085")
    assert(r.status_code == 404)


def test_root_message():
    r = requests.get("http://localhost:8085")
    res = r.json()
    assert(res["message"] == "HTTP 404 Not Found")