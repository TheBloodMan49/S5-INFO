cmake_minimum_required(VERSION 3.27)
project(ProjSuite C)

set(CMAKE_C_STANDARD 90)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -pedantic-errors")

add_executable(ProjSuite main.c)
