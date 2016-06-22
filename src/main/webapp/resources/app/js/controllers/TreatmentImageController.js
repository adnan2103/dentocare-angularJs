'use strict';

/**
 * TreatmentImageController
 * @constructor
 */
var TreatmentImageController = function($scope, $http, $routeParams) {

    $scope.fetchTreatmentImagePaths = function() {
        $http.get('patient/' + $routeParams.id).success(function(patient){
            $scope.patient = patient;
        });

        $http.get('patient/' + $routeParams.id + '/treatment').success(function(treatments){
            $scope.treatments = treatments;

        });
        $scope.state = $routeParams.state;
    };

    $scope.fetchTreatmentImagePaths();

};