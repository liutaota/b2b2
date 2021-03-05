#!/bin/bash
# by lcs
# 2020-07-28

# git hook
#
#  BASE=$(dirname $0)
#  BASE=$(cd $BASE/../..;pwd)
#  read local_ref local_sha remote_ref remote_sha
#  remote_branch="$(echo $remote_ref|awk -F '/' '{print $NF}')"
#
#  if [ "$remote_branch" = "dev" ]
#  then
#    bash $BASE/deploy.sh 0 1
#  fi
#
#  exit 0

BASE=$(dirname $0)
BASE=$(cd "$BASE" || exit;pwd)

read -p "input build branch/tag/SHA name : " -a build_branch
[ -z "$build_branch" ] && exit 1

package_jar="zt-b2b/zt-b2b-api zt-b2b/zt-b2b-service zt-b2b/zt-b2b-oauth-service zt-inca/zt-inca-service zt-b2b-fs/zt-b2b-fs-service zt-b2b-report"
package_select_arr=($package_jar)

len=${#package_select_arr[@]}

for(( i=0;i<$len;i++))
do
  echo "$i - ${package_select_arr[i]}"
done

[ -z "$package_nums" ] && read -p "select package 0-$((len-1))) : " -a package_nums

[ -z "$package_nums" ] && exit 1

echo "package selected"
for(( i=0;i<${#package_nums[@]};i++))
do
  index=${package_nums[i]}
  packaged=${package_select_arr[index]}
  echo "$i - $packaged"
  [ -z "$packaged" ] && exit 1
  deploy_projects="$deploy_projects&deploy_projects=$packaged"
done

[ -z "$deploy_projects" ] && exit 1

build_url="http://zt:11e2c4c7e03848700969c71d33abd2ffd0@jks.zt.gdzsyc.cn/job/zt-b2b/buildWithParameters?token=abs123&build_node=zt"
build_url="$build_url&build_branch=$build_branch&$deploy_projects"

echo "============================="
echo "build_branch: $build_branch"
echo "deploy_projects: ${deploy_projects//&deploy_projects=/ }"
#echo "build_url: $build_url"
echo "============================="

curl --request GET -sL \
     --url "$build_url" \
     -sL