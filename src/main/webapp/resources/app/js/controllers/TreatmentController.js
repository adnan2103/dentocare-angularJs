'use strict';

/**
 * TreatmentController
 * @constructor
 */
var TreatmentController = function($scope, $http, $routeParams) {
    $scope.fetchTreatment = function() {
        $http.get('patient/' + $routeParams.id + '/treatment').success(function(treatments){
            $scope.treatments = treatments;
        });
    };

    $scope.fetchTreatment();
};