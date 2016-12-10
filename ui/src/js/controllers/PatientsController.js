App.controller('PatientsController'['$scope', '$http', 'Post' , function($scope, $http, Post) {
    /*$scope.fetchAllPatients = function() {
        $http.get('patient/all').success(function(allpatients){
            $scope.patients = allpatients;
        });
    };*/

    Post.query(function(data) {
        $scope.patients = data;
    });
    //$scope.fetchAllPatients();

}]);