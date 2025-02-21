<template>

    <v-card outlined>
        <v-card-title>
            OrderPage # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="OrderInfo" v-model="item.orderInfo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RequestInfo" v-model="item.requestInfo" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="OrderStatus" v-model="item.orderStatus" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'OrderPageViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/orderPages/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

