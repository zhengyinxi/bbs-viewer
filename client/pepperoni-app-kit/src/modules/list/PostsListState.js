import {combineReducers} from "redux";
import {Map} from 'immutable';
//const API_SERVER = 'https://www.reddit.com';
const API_SERVER = 'http://192.168.1.225';
//Action


export const REQUEST_POSTS = 'REQUEST_POSTS';
export const RECEIVE_POSTS = 'RECEIVE_POSTS';
export const INVALIDATE_CATEGORY = 'INVALIDATE_CATEGORY';

export function invalidateCategory(category) {
    return {
        type: INVALIDATE_CATEGORY,
        category
    }
}

function requestPosts(category) {
    return {
        type: REQUEST_POSTS,
        category
    }
}

function receivePosts(category, json) {
    return {
        type: RECEIVE_POSTS,
        category,
        posts: json.data.children.map(child => child.data),
        receivedAt: Date.now()
    }
}

function fetchPosts(category) {
    return dispatch => {
        dispatch(requestPosts(category));
        //todo /r
        return fetch(API_SERVER + `/a/${category}.json`)
            .then(response => response.json())
            .then(json => dispatch(receivePosts(category, json)));
    }
}

function shouldFetchPosts(state, category) {
    const posts = state.get('postsList').get('postsByCategory')[category];
    if (!posts) {
        return true
    } else if (posts.isFetching) {
        return false
    } else {
        return posts.didInvalidate
    }
}

export function fetchPostsIfNeeded(category) {
    return (dispatch, getState) => {
        if (shouldFetchPosts(getState(), category)) {
            return dispatch(fetchPosts(category));
        }
    }
}


//Reducer

function posts(state = {
    isFetching: false,
    didInvalidate: false,
    items: []
}, action) {
    switch (action.type) {
        case INVALIDATE_CATEGORY:
            return Object.assign({}, state, {
                didInvalidate: true
            });
        case REQUEST_POSTS:
            return Object.assign({}, state, {
                isFetching: true,
                didInvalidate: false
            });
        case RECEIVE_POSTS:
            return Object.assign({}, state, {
                isFetching: false,
                didInvalidate: false,
                items: action.posts,
                lastUpdated: action.receivedAt
            });
        default:
            return state
    }
}

export function postsByCategory(state = Map({
    selectedCategory: 'reactjs',
    postsByCategory: {}
}), action) {
    switch (action.type) {
        case INVALIDATE_CATEGORY:
        case RECEIVE_POSTS:
        case REQUEST_POSTS:
            var postByCategory = state.get('postsByCategory');
            var newPostByCategory = Object.assign({}, postByCategory, {[action.category]: posts(state[action.category], action)});
            return state.set('postsByCategory', newPostByCategory);
        default:
            return state;
    }
}
