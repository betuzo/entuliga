define([
	'jquery',
	'backbone',
	'bootstrap',
	'selecter',
	'core/BaseView',
	'models/TorneoPartidoModel',
	'text!templates/private/tplPartidoAdmin.html'
], function($, Backbone, bootstrap, selecter, BaseView,
            TorneoPartidoModel, tplPartidoAdmin){

	var PartidoAdminView = BaseView.extend({
        template: _.template(tplPartidoAdmin),

        events: {

        },

        initialize: function() {

        },

        render: function() {
            this.$el.html(this.template());
            return this;
        }
	});

	return PartidoAdminView;

});