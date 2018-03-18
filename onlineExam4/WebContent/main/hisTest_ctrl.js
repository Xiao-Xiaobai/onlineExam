/**
 * Created by lenovo on 2018/3/8.
 */
var htModule = angular.module("app.hisTest", []);
//console.log(asModule);
htModule.controller('hisTestController',['$scope', '$routeParams', '$route', '$rootScope',
    function ($scope, $routeParams, $route, $rootScope) {
       // $scope.$route = $route;
        var sno = $routeParams.sno;

        var stuTestMess = {   "sno": "1111",
                "name": "白菊",
                "historyTests": [{"name": "java期中考试", "subject":"java", "grade": 90},
                    {"name": "web期中考试", "subject":"java", "grade": 80}],
            };
        $scope.hisTests = stuTestMess.historyTests;
        $scope.sNo = sno;
        $scope.sName = stuTestMess.name

}]);