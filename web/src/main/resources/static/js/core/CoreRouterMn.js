define([
  'jquery',
  'backbone',
  'marionette',
], function($, Backbone, Mn) {

  var CoreRouterMn = Mn.AppRouter.extend({

    // initialize: function() {
    //   console.log("initialize MARIONETTE core router");
    // },

    appRoutes: {
      '': 'main',
      '/#': 'main',
      'torneo/:clave': 'publicTorneo',
      'login': 'login',
      // 'login': 'login',
      // 'signup': 'signup',
      // 'token/:token': 'token',
      // 'change/:token': 'changeToken',
      'admin': 'admin',
      'admin/perfil': 'adminPerfil',
      'admin/ligas': 'adminLigas',
      'admin/torneos': 'adminTorneos',
      'admin/torneos/:liga': 'adminTorneos',
      'admin/equipos': 'adminEquipos',
      'admin/jugadores': 'adminJugadores',
      'admin/arbitros': 'adminArbitros',
      'admin/canchas': 'adminCanchas',
      'admin/partido/:partido': 'adminPartido',
      // // 'admin*subroute': 'showAdminView',
      // 'dashboard': 'showDashboard',
      'dashboard/config': 'showDashboardConfig',

      '*action': 'filter',

      // 'dashboard*subroute': 'showDashboard'
    }

  });

  return CoreRouterMn;
});
