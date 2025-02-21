<template>
    <v-card outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            가게 # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            가게
        </v-card-title >        

        <v-card-text style="background-color: white;">
            <String label="가게정보" v-model="value.storeInfo" :editMode="editMode" :inputUI="''"/>
            <String label="보건증" v-model="value.healthCertificate" :editMode="editMode" :inputUI="''"/>
            <Boolean label="StoreApproval" v-model="value.storeApproval" :editMode="editMode" :inputUI="''"/>
            <String label="주문정보" v-model="value.orderInfo" :editMode="editMode" :inputUI="''"/>
            <String label="주문요청서" v-model="value.requestInfo" :editMode="editMode" :inputUI="''"/>
            <FoodStatus offline label="음식상태" v-model="value.foodStatus" :editMode="editMode" @change="change"/>
            <String label="리뷰ID" v-model="value.reviewId" :editMode="editMode" :inputUI="''"/>
            <MenuId offline label="menuId" v-model="value.menuId" :editMode="editMode" @change="change"/>
            <String label="마켓팅정보" v-model="value.marketInfo" :editMode="editMode" :inputUI="''"/>
            <String label="가게 위치" v-model="value.storeLocation" :editMode="editMode" :inputUI="''"/>
            <OrderId offline label="order ID" v-model="value.orderId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions style="background-color: white;">
            <v-spacer></v-spacer>
            <div v-if="!editMode">
                <v-btn
                    color="primary"
                    text
                    @click="edit"
                >
                    수정
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                >
                    삭제
                </v-btn>
            </div>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                    가게 등록
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openCook"
            >
                Cook
            </v-btn>
            <v-dialog v-model="cookDiagram" width="500">
                <CookCommand
                    @closeDialog="closeCook"
                    @cook="cook"
                ></CookCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openNotifyPickup"
            >
                NotifyPickup
            </v-btn>
            <v-dialog v-model="notifyPickupDiagram" width="500">
                <NotifyPickupCommand
                    @closeDialog="closeNotifyPickup"
                    @notifyPickup="notifyPickup"
                ></NotifyPickupCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="approveStore"
            >
                ApproveStore
            </v-btn>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'StoreStore',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
            cookDiagram: false,
            notifyPickupDiagram: false,
        }),
	async created() {
        },
        methods: {
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/stores'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async cook(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['cook'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeCook();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openCook() {
                this.cookDiagram = true;
            },
            closeCook() {
                this.cookDiagram = false;
            },
            async notifyPickup(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['픽업안내'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeNotifyPickup();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openNotifyPickup() {
                this.notifyPickupDiagram = true;
            },
            closeNotifyPickup() {
                this.notifyPickupDiagram = false;
            },
            async approveStore() {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['approvestore'].href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
        },
    }
</script>

