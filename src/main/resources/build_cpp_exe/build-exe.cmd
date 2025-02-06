windres .\OpenVNC.rc -O coff -o .\OpenVNC.res
g++ .\OpenVNC.exe.cpp .\OpenVNC.res -o .\OpenVNC.exe