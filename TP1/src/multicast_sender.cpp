// multicast_sender_windows.cpp
#ifndef _WIN32_WINNT
#define _WIN32_WINNT 0x0A00  // Windows 10/11
#endif

#include <iostream>
#include <cstring>
#include <string>
#include <winsock2.h>
#include <ws2tcpip.h>

#pragma comment(lib, "ws2_32.lib")

int main(int argc, char* argv[]) {
    if (argc != 3) {
        std::cerr << "Usage: " << argv[0] << " <multicast_ip> <port>" << std::endl;
        return 1;
    }

    // Initialisation Winsock
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        std::cerr << "WSAStartup failed" << std::endl;
        return 1;
    }

    const char* multicast_addr = argv[1];
    int port = std::stoi(argv[2]);

    SOCKET sock = socket(AF_INET, SOCK_DGRAM, 0);
    if (sock == INVALID_SOCKET) {
        std::cerr << "socket error: " << WSAGetLastError() << std::endl;
        WSACleanup();
        return 1;
    }

    struct sockaddr_in dest;
    memset(&dest, 0, sizeof(dest));
    dest.sin_family = AF_INET;
    dest.sin_port = htons(port);

    // Convertir l'adresse multicast
    dest.sin_addr.s_addr = inet_addr(multicast_addr);
    if (dest.sin_addr.s_addr == INADDR_NONE) {
        std::cerr << "Invalid multicast address: " << multicast_addr << std::endl;
        closesocket(sock);
        WSACleanup();
        return 1;
    }

    std::string line;
    std::cout << "Begin typing (return to send, Ctrl-C to quit):" << std::endl;
    while (std::getline(std::cin, line)) {
        if (line.empty()) continue;
        int n = sendto(sock, line.c_str(), static_cast<int>(line.size()), 0,
                       (struct sockaddr*)&dest, sizeof(dest));
        if (n == SOCKET_ERROR) {
            std::cerr << "sendto error: " << WSAGetLastError() << std::endl;
            break;
        }
    }
    closesocket(sock);
    WSACleanup();
    return 0;
}