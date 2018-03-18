/**
 * Created by lenovo on 2018/3/8.
 */
var wtModule = angular.module("app.waitTest", []);
wtModule.controller('waitTestController',['$scope', '$routeParams', '$route', '$rootScope',
    function ($scope, $routeParams, $route, $rootScope) {
        var sno = $routeParams.sno;

        var stuTestMess = {   "sno": "2222",
                "name": "小小白",
                "waitTests": [{"name": "java期末考试", "subject":"java"},
                    {"name": "web期末考试", "subject":"web"}],
            };
        $scope.waitTests = stuTestMess.waitTests;
        $scope.sNo = sno;
        $scope.sName = stuTestMess.name

}]);