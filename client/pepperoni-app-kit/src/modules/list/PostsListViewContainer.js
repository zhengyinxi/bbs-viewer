import {connect} from 'react-redux';
import PostsListView from './PostsListView';

function mapStateToProps(state) {
    const  selectedCategory = state.getIn(['postsList','selectedCategory']);
    const postsByCategory = state.getIn(['postsList','postsByCategory']);
    const {
        isFetching,
        lastUpdated,
        items: posts
    } = postsByCategory[selectedCategory] || {
        isFetching: true,
        items: []
    }

    return {
        selectedCategory,
        posts,
        isFetching,
        lastUpdated
    }
}

export default connect(mapStateToProps)(PostsListView);