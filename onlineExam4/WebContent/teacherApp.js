
var teacherApp = angular.module('teacherApp', [
    'ngRoute',
    'app.index_default',
    'app.teacherInfo',
    'app.updatePwd',
    'app.teacherClass',
    'app.teacherStu',
    'app.addStu',
    'app.teacherSub',
    'app.addPaper',
    'app.addExam',
    'app.aeMess',
    'app.apMess',
    'app.stuScore',
    'app.analysisExam'
    
]);

teacherApp.directive('selectLink', [function () {
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
teacherApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/teacher', {
    	controller: 'defaultController',
        templateUrl: 'main/index_default.html'
    }).when('/t_info/:tno', {
    	controller: 'teacherInfoController',
        templateUrl: 'main/t_info.html'
    }).when('/t_updatePwd/:tno', {
    	controller: 'updatePwdController',
        templateUrl: 'main/updatePwd.html'
    }).when('/t_class/:tno', {
    	controller: 'teacherClassController',
        templateUrl: 'main/t_class.html'
    }).when('/t_stu/:tno', {
    	controller: 'teacherStuController',
        templateUrl: 'main/t_stu.html'
    }).when('/t_stu/:tno/:major/:year/:clas', {
    	controller: 'teacherStuController',
        templateUrl: 'main/t_stu.html'
    }).when('/addStu/:tno', {
    	controller: 'addStuController',
        templateUrl: 'main/addStu.html'
    }).when('/t_sub/:tno', {
    	controller: 'teacherSubController',
        templateUrl: 'main/t_sub.html'
    }).when('/addPaper/:tno', {
    	controller: 'addPaperController',
        templateUrl: 'main/addPaper.html'
    }).when('/addPaper/:tno/:eName', {
    	controller: 'addPaperController',
        templateUrl: 'main/addPaper.html'
    }).when('/addPaper/:tno/:eName/:qNo', {
        controller: 'addPaperController',
        templateUrl: 'main/addPaper.html'
    }).when('/addExam/:tno', {
    	controller: 'addExamController',
        templateUrl: 'main/addExam.html'
    }).when('/t_exam/:tno', {
    	controller: 'aeMessController',
        templateUrl: 'main/a_eMess.html'
    }).when('/t_paper/:tno', {
    	controller: 'apMessController',
        templateUrl: 'main/a_pMess.html'
    }).when('/t_paper/:tno/:eId', {
    	controller: 'apMessController',
        templateUrl: 'main/a_pMess.html'
    }).when('/t_stuScore/:tno', {
    	controller: 'stuScoreController',
        templateUrl: 'main/t_stuScore.html'
    }).when('/analysisExam/:tno', {
    	controller: 'analysisExamController',
        templateUrl: 'main/analysisExam.html'
    }).otherwise({
        redirectTo:'/teacher'
    });
}]);