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
    'app.updateStu',
    'app.updateTea',
    'app.stuSub',
    'app.teaSub',
    'app.addTea',
    'app.teaClass',
    'app.asubMess',
    'app.addSub',
    'app.addExam',
    'app.aeMess',
    'app.addPaper',
    'app.apMess',
    'app.aqMess',
    'app.analysisExam'
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
    }).when('/a_subMess', {
        controller: 'asubMessController',
        templateUrl: 'main/a_subMess.html'
    }).when('/a_eMess', {
        controller: 'aeMessController',
        templateUrl: 'main/a_eMess.html'
    }).when('/a_eMess/:sub', {
        controller: 'aeMessController',
        templateUrl: 'main/a_eMess.html'
    }).when('/a_sMess/hisTest/:sno', {
        controller: 'hisTestController',
        templateUrl: 'main/hisTest.html'
    }).when('/a_sMess/waitTest/:sno', {
        controller: 'waitTestController',
        templateUrl: 'main/waitTest.html'
    }).when('/addStudent', {
        controller: 'addStuController',
        templateUrl: 'main/addStu.html'
    }).when('/updateStu/:sno', {
    	controller: 'updateStuController',
    	templateUrl: 'main/updateStu.html'
    }).when('/updateTea/:tno', {
    	controller: 'updateTeaController',
    	templateUrl: 'main/updateTea.html'
    }).when('/a_sMess/stuSub/:sno', {
    	controller: 'stuSubController',
    	templateUrl: 'main/stuSub.html'
    }).when('/a_tMess/teaSub/:tno', {
    	controller: 'teaSubController',
    	templateUrl: 'main/teaSub.html'
    }).when('/a_tMess/teaClass/:tno', {
    	controller: 'teaClassController',
    	templateUrl: 'main/teaClass.html'
    }).when('/addTeacher', {
        controller: 'addTeaController',
        templateUrl: 'main/addTea.html'
    }).when('/addSubject', {
        controller: 'addSubController',
        templateUrl: 'main/addSub.html'
    }).when('/addExam', {
        controller: 'addExamController',
        templateUrl: 'main/addExam.html'
    }).when('/addPaper', {
        controller: 'addPaperController',
        templateUrl: 'main/addPaper.html'
    }).when('/addPaper/:eName', {
        controller: 'addPaperController',
        templateUrl: 'main/addPaper.html'
    }).when('/addPaper/:eName/:qNo', {
        controller: 'addPaperController',
        templateUrl: 'main/addPaper.html'
    }).when('/a_pMess/:eId', {
        controller: 'apMessController',
        templateUrl: 'main/a_pMess.html'
    }).when('/a_qMess', {
        controller: 'aqMessController',
        templateUrl: 'main/a_qMess.html'
    }).when('/a_analysis', {
        controller: 'analysisExamController',
        templateUrl: 'main/analysisExam.html'
    }).otherwise({
        redirectTo:'/'
    });
}]);