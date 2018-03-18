/**
 * Created by lenovo on 2018/3/8.
 */
var app = angular.module('app', [
    'ngRoute',
    'app.index_default',
    'app.asMess',
    'app.atMess',
    'app.hisTest',
    'app.waitTest',
    'app.addStu',
    'app.delStu',
    'app.updateStu',
    'app.stuSub'
]);

app.directive('selectLink', [function () {
    var item =[];
    return {
        restrict: 'A',
        link: function(scope, element, attr){
            item.push(element);
            //事件绑定
            element.bind('click', function (e) {
                item.forEach(function (item) {
                    if(item === element){
                        item.parent().addClass("active");
                    } else{
                        item.parent().removeClass("active");
                    }
                })
            })
        }
    }
}]);

//数据加载
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
    	controller: 'defaultController',
        templateUrl: 'main/index_default.html'
    }).when('/a_sMess', {
        controller: 'asMessController',
        templateUrl: 'main/a_sMess.html'
    }).when('/a_tMess', {
        controller: 'atMessController',
        templateUrl: 'main/a_tMess.html'
    }).when('/a_sMess/hisTest/:sno', {
        controller: 'hisTestController',
        templateUrl: 'main/hisTest.html'
    }).when('/a_sMess/waitTest/:sno', {
        controller: 'waitTestController',
        templateUrl: 'main/waitTest.html'
    }).when('/addStudent', {
        controller: 'addStuController',
        templateUrl: 'main/addStu.html'
    }).when('/deleteStu/:sno', {
    	controller: 'delStuController',
    	templateUrl: 'main/a_sMess.html'
    }).when('/updateStu/:sno', {
    	controller: 'updateStuController',
    	templateUrl: 'main/updateStu.html'
    }).when('/a_sMess/stuSub/:sno', {
    	controller: 'stuSubController',
    	templateUrl: 'main/stuSub.html'
    }).otherwise({
        redirectTo:'/'
    });
}]);