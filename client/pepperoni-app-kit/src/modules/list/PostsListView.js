import React, { Component, PropTypes } from 'react';
import {fetchPostsIfNeeded, invalidateCategory} from "./PostsListState";
import {StyleSheet, View, Text,TouchableOpacity} from "react-native";
import NavigationState from "../navigation/NavigationViewContainer";


const PostsListView = React.createClass( {
    propTypes : {
        selectedCategory: PropTypes.string.isRequired,
        posts: PropTypes.array.isRequired,
        isFetching: PropTypes.bool.isRequired,
        lastUpdated: PropTypes.number,
        dispatch: PropTypes.func.isRequired
    },

    handleRefreshClick(e){
        e.preventDefault();
        const {dispatch, selectedCategory} = this.props;
        dispatch(invalidateCategory(selectedCategory));
        dispatch(fetchPostsIfNeeded(selectedCategory));

    },

    handleTitleClick(e){
        e.preventDefault();
        // dispatch(NavigationState.pushRoute({
        //     key: 'Content',
        //     title: 'Content'
        // }));
    },

    componentWillMount() {
        console.log("componentWillMount");
    },

    componentWillReceiveProps(nextProps) {
        console.log("componentWillReceiveProps");
    },


    componentDidMount() {
        const { dispatch, selectedCategory } = this.props;
        dispatch(invalidateCategory(selectedCategory));
        dispatch(fetchPostsIfNeeded(selectedCategory));
    },


    render() {
        const { selectedCategory, posts, isFetching, lastUpdated } = this.props;
        return (
            <View style={styles.container}>
                <View>
                    {lastUpdated &&
                        <Text>
                            Last updated at {new Date(lastUpdated).toLocaleTimeString()}.{' '}
                        </Text>
                    }
                    {/*//{!isFetching &&*/}
                    <TouchableOpacity href='#'
                       onPress={this.handleRefreshClick}>
                        <Text style={styles.linkButton}>
                            Refresh
                        </Text>
                    </TouchableOpacity>
                    {/*//}*/}
                </View>
                {isFetching && posts.length === 0 &&
                <Text>Loading...</Text>
                }
                {!isFetching && posts.length === 0 &&
                <h2>Empty.</h2>
                }
                {posts.length > 0 &&
                <View style={{ opacity: isFetching ? 0.5 : 1 }}>

                        {posts.map((post, i) =>
                            <View key={i}>
                                <Text>{post.title}</Text>
                                <TouchableOpacity
                                    onPress={this.handleTitleClick}
                                >

                                </TouchableOpacity>
                            </View>
                        )}

                </View>
                }
            </View>
        )
    }
});

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'white'
    },
    linkButton:{

    }
});

export default PostsListView;