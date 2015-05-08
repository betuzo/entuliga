define([
	'jquery',
	'underscore',
	'backbone',
	'core/BaseView',
	'text!templates/tplResumenHotel.html'
], function($, _, Backbone, BaseView, tplResumenHotel){

	var ResumenHotelView = BaseView.extend({
        el: '.list-group',
        template: _.template(tplResumenHotel),

        events: {
            'click #ir-detalle': 'showDetailHotel'
        },

        initialize: function() {
            this.render();
        },

        render: function() {
            this.$el.html(this.template(this.model.toJSON()));
        },

        showDetailHotel: function(event){
            Backbone.history.navigate('hotel/' + this.model.get('id') + '/detail', {trigger: true});
        }
	});

	return ResumenHotelView;

});