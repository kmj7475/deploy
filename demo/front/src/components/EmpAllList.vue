<template>
  <table>
    <thead>
      <tr>
        <th v-for="(info, index) in headerInfo" v-bind:key="index">{{ info }}</th>
      </tr>
    </thead>
    <tbody>
      <template v-for="(empInfo, index) in empList" >
        <tr v-bind:key="index">
          <td v-for="(info, index) in headerInfo" v-bind:key="index">{{  empInfo[info] }}</td>
        </tr>
      </template>
    </tbody>
  </table>
</template>

<script>
const axios = require('axios');

export default {
  name : 'EmpAllList',
  data : function(){
    return {
      headerInfo : ['employeeId', 'firstName', 'lastName', 'email', 'jobId'],
      empList : []
    }
  },
  created : function(){
    axios.get('/boot/emps')
    .then(response => response.data)
    .then(data => this.empList = data);
  }

}
</script>

<style scoped>
  table, tr, tr, td {
    border : 1px solid black;
  }
</style>