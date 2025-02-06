#include <windows.h>

int main() {
    ShowWindow(GetConsoleWindow(), SW_HIDE);
    system(".\\jre\\bin\\java -jar .\\quarkus-app\\quarkus-run.jar");
    return 0;
}
