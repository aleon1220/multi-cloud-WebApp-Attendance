#!/bin/bash
set -e

# Array  avoids word splitting issues
COMMANDS=(help build clean run_smoke_test run_stack test_stack)
export APP_WAR_FILE_VERSION=$(gradle getAppVersion --quiet) || true
# build
version="$APP_WAR_FILE_VERSION"
command=$1

print_usage() {
  printf "Usage: %s <command> [options] Available commands: \n\n" "$0"
  printf "\t %s " "${COMMANDS[@]}"
  printf "\n\n Script tested in Linux Bash 5.1.16 \n\n"
  printf "This OS bash version is: \n\n" 
  bash --version || true  
}

# Function to handle invalid command
invalid_command() {
  printf "Invalid command: %s \n\n" "$1"
  print_usage
  exit 1
}

build() {
#  gradle clean assemble war --warn || true
  gradle clean war --warn || true
  docker build --build-arg APP_WAR_FILE_VERSION="$version" --tag aleon1220/attendance-webapp:"$version" --file Dockerfile.wildfly . || true
}

run_smoke_test() {
  clean "$version"
  build
  printf "Executing webapp Locally \n\n" || true
  docker run --interactive --tty --detach --publish 8080:8080 --name $version aleon1220/attendance-webapp:$version || true
  source .env
  gradle getProjectInfo
  printf "\t\tLoaded .env variables file succesfully\n\n"
  printf "\t\tExecuting Java Webapp Attendance version %s\n" $APP_WAR_FILE_VERSION || true
  printf "\t\tExecuting Java Webapp Attendance version %s\n" $version
}

clean() {
  # Check if a version argument is provided
  if [ -z "$2" ]; then
    version="$version"
  else
    # Version provided, use it directly
    version="$2"
  fi
  printf "Executing local CLEAN-UP \n\n"
  docker kill $version
  docker rm $version
  docker image rm aleon1220/attendance-webapp:$version
  printf "docker clean up completed \n\n"
}

test_stack() {
  printf "Executing TESTING \n\n"
  gradle test || true
}

run_stack() {
  clean
  build
  test_stack
  run
  printf "Executing webapp stack Locally \n\n" || true
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
