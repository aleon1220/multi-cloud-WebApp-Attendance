#!/bin/bash
set -e

# Available commands
COMMANDS="build run clean test help"

print_usage() {
  echo "Usage: $0 <command> [options]"
  echo "Available commands:"
  printf "\t %s " $COMMANDS
  printf "\n\n script tested in Linux Bash 5.1.16 \n\n"
  printf "This OS bash version is: \n" 
  bash --version || true  
}

# Function to handle invalid command
invalid_command() {
  echo "Invalid command: '$1'"
  print_usage
  exit 1
}

# Main script logic
if [[ $# -eq 0 ]]; then
  print_usage
  exit 1
fi

command="$1"
shift

case "$command" in
  $COMMANDS)
    # Call specific function based on the command
    "$command" "$@" || true
    ;;
  *)
    invalid_command "$command"
    ;;
esac

# build
export APP_WAR_FILE_VERSION=$(gradle getAppVersion --quiet) || true

build() {
gradle clean war --warn || true
docker build --build-arg APP_WAR_FILE_VERSION=$APP_WAR_FILE_VERSION --tag aleon1220/soa:$APP_WAR_FILE_VERSION .
}
# run

# clean

# test

# help
help() {
  print_usage
}