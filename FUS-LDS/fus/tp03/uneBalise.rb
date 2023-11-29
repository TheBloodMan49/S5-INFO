#!/usr/bin/ruby -n

print $_.gsub(/<\/[^\/<>]+>/,"\\0\n").gsub(/<[^\/<>]+>/,"\\0\n")