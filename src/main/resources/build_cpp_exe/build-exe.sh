#!/bin/bash

# Build the Resource, for example ICON information
windres ./OpenVNC.rc -O coff -o ./OpenVNC.res

# Build the EXE using the generated Resource
g++ ./OpenVNC.exe.cpp ./OpenVNC.res -o ./OpenVNC.exe
