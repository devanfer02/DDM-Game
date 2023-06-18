#!/bin/bash

if [-z "$1"]; then
    echo "Masukkan URL git"
    exit 1
fi

git init
git branch -M main
git add .
git commit -m "init"
git remote add origin "$1"
git push origin main

echo "Github repo sudah terinisialiasi"