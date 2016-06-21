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

                $scope.treatments = [
                    {
                        "id":null,
                        "chiefComplaintDescription":"",
                        "notes":"",
                        "status":"In-Progress",
                        "payment":[
                            {
                                "id":null,
                                "paymentDate":"",
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
                    }
                ];
                $scope.payment = $scope.treatments[0].payment;
                $scope.patientOralExamination = $scope.treatments[0].patientOralExamination;
            } else {
                $scope.payment = treatments[0].payment;
                $scope.patientOralExamination = treatments[0].patientOralExamination;
            }
            $scope.totalCost = 0;
            $scope.paymentMade = 0;
            $scope.updateTotalCost();
            $scope.updateTotalPayment();

        });
    };

    $scope.updateTotalCost = function() {
        var cost = 0;
        angular.forEach($scope.treatments[0].patientOralExamination, function(patientOralExamination) {
            cost += patientOralExamination.cost;
        });
        $scope.totalCost = cost;
        $scope.pendingPayment = $scope.totalCost - $scope.paymentMade;
    }

    $scope.updateTotalPayment = function() {
        var totalPayment = 0;
        angular.forEach($scope.treatments[0].payment, function(payment) {
            totalPayment += payment.paymentAmount;
        });
        $scope.paymentMade = totalPayment;

        $scope.pendingPayment = $scope.totalCost - $scope.paymentMade;
    }


    $scope.saveTreatment = function(treatments) {
        $scope.resetError();

        $http.put('patient/' + $routeParams.id + '/treatment', treatments).success(function(treatments) {
            $scope.treatments = treatments;
            $scope.payment = treatments[0].payment;
            $scope.patientOralExamination = treatments[0].patientOralExamination;
            $scope.message = 'Treatment Updated.';
        }).error(function() {
            $scope.setError('Could not update the treatment.');
        });
    };

    $scope.addPayment = function() {
        var newPayment =
            {
                "id":null,
                "paymentDate": new Date(),//$filter('date')(new Date(),'dd-MM-yyyy'),
                "paymentAmount":0,
                "treatmentDone":""
            };

        $scope.payment.push(newPayment);
    };

    $scope.addPatientOralExamination = function() {
        var newPatientOralExamination =
        {
            "id":null,
            "description" : "",
            "cost" : 0
        };

        $scope.patientOralExamination.push(newPatientOralExamination);
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

    $scope.dateOptions = {
        changeYear: true,
        changeMonth: true,
        yearRange: '1900:-0'
    };
};