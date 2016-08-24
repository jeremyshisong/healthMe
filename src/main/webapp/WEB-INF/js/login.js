angular.module('loginApp', []).controller('loginCtrl', function ($scope, $http, $location) {
  $scope.login = function () {
    $http.get("https://leancloud.cn:443/1.1/login?username=" + $scope.email + "&password=" + $scope.password, {
        headers: {'X-LC-Id': 'lehRrcPFUSbhSBVqDxNSocVV-gzGzoHsz', 'X-LC-Key': 'tuEz3gPGmS54YsTWbRoqEg5c'}
      })
      .then(function Succes(response) {
        alert(response.data.email);
        $location.url("index.html");
      }, function Error(response) {
        alert(response.status);
      });
  }
});
