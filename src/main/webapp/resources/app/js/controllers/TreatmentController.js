'use strict';

/**
 * TreatmentController
 * @constructor
 */
var TreatmentController = function($scope, $http, $routeParams) {

    $scope.status = [
        {'status': 'In-Progress'},
        {'status': 'Closed'}
    ];

    $scope.fetchTreatment = function() {
        $http.get('patient/' + $routeParams.id).success(function(patient){
            $scope.patient = patient;
        });

        $http.get('patient/' + $routeParams.id + '/treatment').success(function(treatments){
            $scope.treatments = treatments;

            if(!treatments[0]) {

                $scope.treatments[0] = treatment;
                /*$scope.payment = $scope.treatments[0].payment;
                $scope.patientOralExamination = $scope.treatments[0].patientOralExamination;*/
            } else {
                /*$scope.payment = treatments[0].payment;
                $scope.patientOralExamination = treatments[0].patientOralExamination;*/
            }
            $scope.totalCost = [0];
            $scope.paymentMade = [0];
            $scope.pendingPayment = [0];

            $scope.updateTotalCost(0);
            $scope.updateTotalPayment(0);

        });
    };


    var treatment = {
            "id":null,
            "chiefComplaintDescription":"",
            "notes":"",
            "status":"In-Progress",
            "payment":[
                {
                    "id":null,
                    "paymentDate":new Date(),
                    "paymentAmount":0,
                    "treatmentDone":""
                }
            ],
            "patientOralExamination":[
                {
                    "id":null,
                    "description":"",
                    "cost":0
                }
            ]
        };

    $scope.updateTotalCost = function(index) {
        var cost = 0;
        angular.forEach($scope.treatments[index].patientOralExamination, function(patientOralExamination) {
            cost += patientOralExamination.cost;
        });
        $scope.treatments[index].totalCost.push(cost);
        $scope.totalCost[index] = cost;
        $scope.pendingPayment[index] = $scope.totalCost[index] - $scope.paymentMade[index];
    }

    $scope.updateTotalPayment = function(index) {
        alert(index);

        var totalPayment = 0;
        angular.forEach($scope.treatments[index].payment, function(payment) {
            totalPayment += payment.paymentAmount;
        });
        $scope.paymentMade[index] = totalPayment;

        $scope.pendingPayment[index] = $scope.totalCost[index] - $scope.paymentMade[index];
    }


    $scope.saveTreatment = function(treatments) {
        $scope.resetError();

        $http.put('patient/' + $routeParams.id + '/treatment', treatments).success(function(treatments) {
            $scope.treatments = treatments;
            $scope.message = 'Treatment Updated.';
            alert('Treatment Added/Updated Successfully.');
        }).error(function(error) {
            alert(error);
            $scope.setError('Could not update the treatment.');
        });
    };

    $scope.addPayment = function(index) {
        var newPayment =
            {
                "id":null,
                "paymentDate": new Date(),//$filter('date')(new Date(),'dd-MM-yyyy'),
                "paymentAmount":0,
                "treatmentDone":""
            };

        $scope.treatments[index].payment.push(newPayment);
    };

    $scope.addPatientOralExamination = function(index) {
        var newPatientOralExamination =
        {
            "id":null,
            "description" : "",
            "cost" : 0
        };

        $scope.treatments[index].patientOralExamination.push(newPatientOralExamination);
    };

    $scope.addNewTreatment = function(size) {
        alert('Treatment Added, Click save to permanently add it.');
        $scope.treatments[size] = treatment;
    }

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchTreatment();

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
};