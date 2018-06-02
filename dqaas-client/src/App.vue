<template>
<div>
  <nav class="navbar is-transparent">
    <div class="navbar-brand">
      <a class="navbar-item" href="/">
        <img src="./assets/ams-ber.png" alt="Atlas" width="60" height="100">
      </a>
      <div class="navbar-burger burger" data-target="navbarExampleTransparentExample">
        <span></span>
        <span></span>
        <span></span>
      </div>
    </div>
    <div id="navbarExampleTransparentExample" class="navbar-menu">
      <div class="navbar-start">
        <a class="navbar-item" href="/#">
          DMAAS Studio
        </a>
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link" href="#/browse">
            Browse
          </a>
          <div class="navbar-dropdown is-boxed">
            <a class="navbar-item" href="#/browse/tables">
              Tables
            </a>
            <a class="navbar-item" href="/#/createrule">
              Governance Rule
            </a>
          </div>
        </div>
      </div>
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="field is-grouped">
            <p class="control">
            </p>
            <p class="control">
              <a class="button is-primary" href="/#/login">
                <span class="login">
                </span>
                <span>Login</span>
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <div id="app">
    <router-view/>
  </div>
</div>
</template>
<script>

export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2f3b47;
  margin-top: 60px;
}
</style>
