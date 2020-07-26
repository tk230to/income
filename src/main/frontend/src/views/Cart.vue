<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h1>カート</h1>

        <div
          v-for="(item, index) in this.$store.state.cart"
          :key="index"
        >{{item.name}} : {{item.count}}</div>
      </div>
    </div>
  </div>
</template>
