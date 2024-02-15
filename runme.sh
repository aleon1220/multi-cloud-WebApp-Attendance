#!/bin/bash
set -e

# Array  avoids word splitting issues
COMMANDS=(build run clean test help)
export APP_WAR_FILE_VERSION=$(gradle getAppVersion --quiet) || true
command=$1

print_usage() {
  printf "Usage: %s <command> [options] Available commands: \n\n" $0
  printf "\t %s " "${COMMANDS[@]}"
  printf "\n\n Script tested in Linux Bash 5.1.16 \n\n"
  printf "This OS bash version is: \n\n" 
  bash --version || true  
}

# Function to handle invalid command
invalid_command() {
  printf "Invalid command: %s \n\n" $1
  print_usage
  exit 1
}

# build
export APP_WAR_FILE_VERSION=$(gradle getAppVersion --quiet) || true

build() {
gradle clean war --warn || true
docker build --build-arg APP_WAR_FILE_VERSION=$APP_WAR_FILE_VERSION --tag aleon1220/soa:$APP_WAR_FILE_VERSION .
}

run() {
  printf "Executing webapp Locally \n\n"
}

clean() {
  printf "Executing CLEAN-UP\n\n"
  gradle clean || true
}

test() {
printf "Executing TESTING \n\n"
}

help() {
  printf "Help Function Java WebApp Attendance Class \n\n"
  print_usage
}

if [[ " ${COMMANDS[@]} " = *" $command "* ]]; then
  "$command" "$@" || true
else
  invalid_command "$command"
fi
