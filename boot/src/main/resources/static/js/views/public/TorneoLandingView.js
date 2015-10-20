define([
	'jquery',
	'bootstrap',
	'core/BaseView',
	'models/TorneoModel',
	'text!templates/public/tplTorneoLanding.html'
], function($, bootstrap, BaseView, TorneoModel, tplTorneoLanding){

	var TorneoLandingView = BaseView.extend({
        template: _.template(tplTorneoLanding),

        events: {
        },

        initialize: function(opts) {
            console.log(opts);

            this.model = new TorneoModel();
            this.model.set({id: opts.clave});
            this.listenTo(this.model, 'sync', this.syncTorneoSuccess);
            this.model.fetch();
        },

        render: function() {
            return this;
        },

        syncTorneoSuccess: function() {
            this.$el.html(this.template(this.model.toJSON()));
        }
	});

	return TorneoLandingView;

});