/**
 * Created by lenovo on 2018/3/8.
 */
var studentApp = angular.module('studentApp', [
    'ngRoute',
    'app.index_default',
    'app.studentSub',
    'app.studentInfo',
    'app.updatePwd',
    'app.studentExam',
    'app.studentWaitTest',
    'app.studentHisTest'
]);

studentApp.directive('selectLink', [function () {
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
studentApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/student', {
    	controller: 'defaultController',
        templateUrl: 'main/index_default.html'
    }).when('/s_subMess/:sno', {
    	controller: 'studentSubController',
        templateUrl: 'main/s_subMess.html'
    }).when('/s_info/:sno', {
    	controller: 'studentInfoController',
        templateUrl: 'main/s_info.html'
    }).when('/s_updatePwd/:sno', {
    	controller: 'updatePwdController',
        templateUrl: 'main/updatePwd.html'
    }).when('/s_test/:sno', {
    	controller: 'studentExamController',
        templateUrl: 'main/s_testlist.html'
    }).when('/s_waitTest/:sno', {
    	controller: 'studentWaitTestController',
        templateUrl: 'main/s_waitTest.html'
    }).when('/s_hisTest/:sno', {
    	controller: 'studentHisTestController',
        templateUrl: 'main/s_hisTest.html'
    }).otherwise({
        redirectTo:'/student'
    });
}]);