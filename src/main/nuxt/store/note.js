export const state = () => ({
  data: {
    update: 0,
    list: []
  }
})

export const getters = {
  listNotes (state){
    return state.data.list;
  }
}


export const mutations = {
  remove(state, note) {
    const index = state.data.list.findIndex(item => item.id === note.id);
    state.data.list.splice(index, 1);
    state.data.update++;
  },
  create(state, note) {
    state.data.list.unshift(note);
    state.data.update++;
  },
  update(state, note) {
    const index = state.data.list.findIndex(item => item.id === note.id);
    if (index >= 0){
      state.data.list.splice(index, 1, note);
      state.data.update++;
    }
  },
  load(state, notes) {
    if (!Array.isArray(notes))
      notes = [notes]
    state.data.list = notes;
  },
  delete (state){
    state.data.list = [];
  }
}