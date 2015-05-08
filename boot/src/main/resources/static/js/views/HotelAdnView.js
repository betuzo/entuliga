define([
	'jquery',
	'core/BaseView',
	'views/ResumenHotelView',
	'collections/HotelesCollection',
	'text!templates/tplHotelAdn.html'
], function($, BaseView, ResumenHotelView, HotelesCollection, tplHotelAdn){

	var HotelAdnView = BaseView.extend({
        template: _.template(tplHotelAdn),

        events: {
            'change #input-hotel': 'showResumenHotel'
        },

        initialize: function() {
            app.hoteles = new HotelesCollection();
            this.listenTo(app.hoteles, 'add', this.agregarHotel);
            this.listenTo(app.hoteles, 'sync', this.syncHoteles);
            app.hoteles.fetch();
        },

        render: function() {
            this.$el.html(this.template());
            return this;
        },

        syncHoteles: function(){
            $('#input-hotel').change();
        },

        agregarHotel: function(modelo){
            $('#input-hotel').append($('<option>', {
                value: modelo.get('id'),
                text : modelo.get('nombreOficial')
            }));
        },

        showResumenHotel: function(event){
            var modelo = app.hoteles.get($(event.target).val());
            new ResumenHotelView({model: modelo});
        }
	});

	return HotelAdnView;

});