cmake_minimum_required(VERSION 3.0)
project(ProjNorme C)


set(CMAKE_C_STANDARD 90)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall -Wextra -pedantic-errors")

add_executable(ProjNorme main.c)
target_link_libraries(ProjNorme m)
