var app = angular.module('personApp', []);

app.controller('PersonController', function ($scope, PersonResource) {

    $scope.person = {};

    listar();

    function listar() {
        PersonResource.listar().then(function (person) {
            $scope.person = resposta.data;
        });
    }

    $scope.salvar = function (person) {
        PersonResource.salvar(person).then(listar);
        $scope.person = {};
    };

    $scope.editar = function (person) {
        $scope.person = angular.copy(person);
    };

    $scope.excluir = function (person) {
        PersonResource.excluir(person).then(listar);
    };

    $scope.cancelar = function () {
        $scope.person = {};
    };
});

app.service('PersonResource', function ($http) {

    var api = 'http://localhost:8080/restapi/api/person/';

    this.listar = function () {
        return $http.get(api);
    };

    this.salvar = function (person) {
        if (person.id) {
            return $http.put(api + '/' + person.id, person);
        } else {
            return $http.post(api, person);
        }
    };

    this.excluir = function (person) {
        return $http.delete(api + '/' + person.id);
    };

});
