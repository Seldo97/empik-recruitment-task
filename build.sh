#!/bin/bash

# Budowanie aplikacji za pomocą Mavena
echo "Building the application using Maven..."
mvn clean package

# Sprawdzenie, czy budowanie aplikacji się powiodło
if [ $? -ne 0 ]; then
  echo "Build failed! Exiting..."
  exit 1
fi

# Budowanie obrazu Docker, jeśli budowanie aplikacji się powiodło
echo "Building the Docker image..."
docker build -t marcinolek:latest .

# Sprawdzenie, czy budowanie obrazu Docker się powiodło
if [ $? -ne 0 ]; then
  echo "Docker image build failed! Exiting..."
  exit 1
fi

echo "Build and Docker image creation successful!"