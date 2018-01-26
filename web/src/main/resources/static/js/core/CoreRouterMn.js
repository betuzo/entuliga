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
      'admin/partido/:partido': 'adminPartido',

      // 'login': 'login',
      // 'admin': 'admin',
      // 'admin/perfil': 'adminPerfil',
      // 'admin/ligas': 'adminLigas',
      // 'admin/torneos': 'adminTorneos',
      // 'admin/torneos/:liga': 'adminTorneos',
      // 'admin/equipos': 'adminEquipos',
      // 'admin/jugadores': 'adminJugadores',
      // 'admin/arbitros': 'adminArbitros',
      // 'admin/canchas': 'adminCanchas',
      // 'dashboard/config': 'showDashboardConfig',
      '*action': 'filterurl',
    }

  });

  return CoreRouterMn;
});
