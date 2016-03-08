'use strict';

/**
 * TreatmentController
 * @constructor
 */
var TreatmentController = function($scope, $http, $routeParams) {
    $scope.fetchTreatment = function() {

        $http.get('patient/' + $routeParams.id).success(function(patient){
            $scope.patient = patient;
        });

        $http.get('patient/' + $routeParams.id + '/treatment').success(function(treatments){
            $scope.treatments = treatments;
        });
    };

    $scope.saveTreatment = function(treatments) {
        $scope.resetError();

        $http.put('patient/' + $routeParams.id + '/treatment', treatments).success(function() {
            $scope.message = 'Treatment Updated.';
        }).error(function() {
            $scope.setError('Could not update the treatment.');
        });
    };

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchTreatment();
};