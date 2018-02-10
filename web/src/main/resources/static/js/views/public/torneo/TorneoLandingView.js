define([
  'jquery',
  'bootstrap',
  'core/BaseView',
  'models/TorneoModel',
  'views/public/torneo/TorneoJornadaView',
  'views/public/torneo/TorneoPosicionesView',
  'views/public/torneo/TorneoLideresView',
  'text!templates/public/torneo/tplTorneoLanding.html'
], function($, bootstrap, BaseView, TorneoModel,
  TorneoJornadaView, TorneoPosicionesView,
  TorneoLideresView, tplTorneoLanding) {

  var TorneoLandingView = BaseView.extend({
    template: _.template(tplTorneoLanding),

    events: {},

    initialize: function(opts) {
      this.model = new TorneoModel();
      this.model.set({ id: opts.clave });
      this.listenTo(this.model, 'sync', this.syncTorneoSuccess);
      this.model.fetch();
    },

    render: function() {
      return this;
    },

    syncTorneoSuccess: function() {
      this.$el.html(this.template(this.model.toJSON()));
      new TorneoJornadaView(this.model);
      new TorneoLideresView(this.model);
      new TorneoPosicionesView(this.model);
    }
  });

  return TorneoLandingView;

});
