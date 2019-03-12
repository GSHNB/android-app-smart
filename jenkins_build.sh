#!/usr/bin/env bash
build_number=$1

if [ -z $build_number ]; then
    build_number=0
fi

build_type=$2

if [ -z $build_type ]; then
    build_type="Test"
fi

root_path="`dirname $0`"
root_path_prefix=${root_path}
if [ "$root_path_prefix" != "/" ]; then
    root_path="`pwd`/${root_path}"
fi
root_path="`pwd`"
echo ${root_path}
if [ ! -z "$build_number" ]  && [ ! -z "$root_path" ]; then
        rm -rf ${root_path}/output/Build_${build_number}
fi

mkdir -p ${root_path}/output/Build_${build_number}

if [ "$build_type" = "Test" ]; then
    echo ${root_path}
    ${root_path}/gradlew --no-daemon clean assembleDemoRelease -PBUILD_NUMBER=${build_number} || exit -1
    ${root_path}/gradlew --no-daemon clean assembleOnlineRelease -PBUILD_NUMBER=${build_number} || exit -1
else
    ${root_path}/gradlew --no-daemon clean assembleOnlineReleaseChannels -PBUILD_NUMBER=${build_number} || exit -1
fi
