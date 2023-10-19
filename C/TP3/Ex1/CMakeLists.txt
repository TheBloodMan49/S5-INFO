cmake_minimum_required(VERSION 3.0)
project(Ex1 C)


set(CMAKE_C_STANDARD 90)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -pedantic-errors")

add_executable(Ex1 main.c
        tableau.c)
target_link_libraries(Ex1 m)
