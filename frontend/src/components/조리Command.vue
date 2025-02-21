<template>
    <v-card outlined>
        <v-card-title>
            조리
        </v-card-title>

        <v-card-text>
            <FoodStatus offline label="FoodStatus" v-model="value.foodStatus" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="조리"
            >
                조리
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: '조리Command',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.foodStatus = {};
        },
        watch: {
        },
        methods: {
            조리() {
                this.$emit('조리', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

