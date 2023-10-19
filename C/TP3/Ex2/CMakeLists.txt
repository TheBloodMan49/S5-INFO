cmake_minimum_required(VERSION 3.27)
project(Ex2 C)

set(CMAKE_C_STANDARD 90)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -pedantic-errors")

add_executable(Ex2 main.c
        login.c)
